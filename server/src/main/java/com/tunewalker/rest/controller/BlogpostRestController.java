package com.tunewalker.rest.controller;

import com.google.gson.Gson;
import com.tunewalker.rest.dto.BlogpostDTO;
import com.tunewalker.rest.model.AlbumArtDetails;
import com.tunewalker.rest.model.Blogpost;
import com.tunewalker.rest.model.CouldaShouldaDetails;
import com.tunewalker.rest.model.MapDetails;
import com.tunewalker.rest.service.BlogpostService;
import com.tunewalker.rest.util.ObjectMapperUtils;
import com.tunewalker.rest.util.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/blogpost")
public class BlogpostRestController {

    @Autowired
    private BlogpostService blogpostService;
    @GetMapping(value = "/list")
    public ResponseEntity<List<BlogpostDTO>> getAllBlogposts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ObjectMapperUtils.mapAll(blogpostService.findAll(), BlogpostDTO.class));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BlogpostDTO> getBlogpostById(@PathVariable("id") String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ObjectMapperUtils.map(blogpostService.findByBlogpostId(id), BlogpostDTO.class));
    }

    @PostMapping()
    public ResponseEntity<String> uploadBlogpost(
            @RequestParam("blogpost_hero_photo") MultipartFile blogpostHeroPhoto,
            @RequestParam("blogpost_album_art_1") MultipartFile blogpostAlbumArt1,
            @RequestParam("blogpost_album_art_2") MultipartFile blogpostAlbumArt2,
            @RequestParam("blogpost_coulda_shoulda_album_art_1") MultipartFile blogpostCouldaAlbumArt1,
            @RequestParam("blogpost_coulda_shoulda_album_art_2") MultipartFile blogpostCouldaAlbumArt2,
            @RequestParam("blogpost_map") MultipartFile blogpostMap,

            @RequestPart("blogpost_album_1_rank") String blogpostAlbum1Rank,
            @RequestPart("blogpost_album_2_rank") String blogpostAlbum2Rank,
            @RequestPart("blogpost_title") String blogpostTitle,
            @RequestPart("blogpost_intro") String blogpostIntro,
            @RequestPart("blogpost_review_1") String blogpostReview1,
            @RequestPart("blogpost_review_2") String blogpostReview2,
            @RequestPart("blogpost_hero_photo_caption") String blogpostHeroCaption,
            @RequestPart("blogpost_album_art_1_details") String blogpostAlbumArt1Details,
            @RequestPart("blogpost_album_art_2_details") String blogpostAlbumArt2Details,
            @RequestPart("blogpost_map_details") String blogpostMapDetails,
            @RequestPart("blogpost_coulda_shoulda_1_details") String blogpostCoulda1Details,
            @RequestPart("blogpost_coulda_shoulda_2_details") String blogpostCoulda2Details
    ) {

        try {
            String prefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "_";
            S3Util.uploadFile(prefix + blogpostHeroPhoto.getOriginalFilename(), blogpostHeroPhoto.getInputStream());
            S3Util.uploadFile(prefix + blogpostAlbumArt1.getOriginalFilename(), blogpostAlbumArt1.getInputStream());
            S3Util.uploadFile(prefix + blogpostAlbumArt2.getOriginalFilename(), blogpostAlbumArt2.getInputStream());
            S3Util.uploadFile(prefix + blogpostCouldaAlbumArt1.getOriginalFilename(), blogpostCouldaAlbumArt1.getInputStream());
            S3Util.uploadFile(prefix + blogpostCouldaAlbumArt2.getOriginalFilename(), blogpostCouldaAlbumArt2.getInputStream());
            S3Util.uploadFile(prefix + blogpostMap.getOriginalFilename(), blogpostMap.getInputStream());

            AlbumArtDetails albumArtDetails1 = getAlbumArtDetails(blogpostAlbumArt1Details);
            AlbumArtDetails albumArtDetails2 = getAlbumArtDetails(blogpostAlbumArt2Details);
            MapDetails mapDetails = getMapDetails(blogpostMapDetails);
            CouldaShouldaDetails couldaShouldaDetails1 = getCouldaShouldaDetails(blogpostCoulda1Details);
            CouldaShouldaDetails couldaShouldaDetails2 = getCouldaShouldaDetails(blogpostCoulda2Details);

            BlogpostDTO blogpostDTO = new BlogpostDTO();
            blogpostDTO.setBlogpost_album_1_rank(blogpostAlbum1Rank);
            blogpostDTO.setBlogpost_album_2_rank(blogpostAlbum2Rank);
            blogpostDTO.setBlogpost_title(blogpostTitle);
            blogpostDTO.setBlogpost_intro(blogpostIntro);
            blogpostDTO.setBlogpost_review_1(blogpostReview1);
            blogpostDTO.setBlogpost_review_2(blogpostReview2);
            blogpostDTO.setBlogpost_hero_photo_caption(blogpostHeroCaption);
            blogpostDTO.setBlogpost_album_art_1_details(albumArtDetails1);
            blogpostDTO.setBlogpost_album_art_2_details(albumArtDetails2);
            blogpostDTO.setBlogpost_map_details(mapDetails);
            blogpostDTO.setBlogpost_coulda_shoulda_1_details(couldaShouldaDetails1);
            blogpostDTO.setBlogpost_coulda_shoulda_2_details(couldaShouldaDetails2);
            blogpostDTO.setBlogpost_hero_photo(S3Util.S3_URL + prefix + blogpostHeroPhoto.getOriginalFilename());
            blogpostDTO.setBlogpost_album_art_1(S3Util.S3_URL + prefix + blogpostAlbumArt1.getOriginalFilename());
            blogpostDTO.setBlogpost_album_art_2(S3Util.S3_URL + prefix + blogpostAlbumArt2.getOriginalFilename());
            blogpostDTO.setBlogpost_coulda_shoulda_album_art_1(S3Util.S3_URL + prefix + blogpostCouldaAlbumArt1.getOriginalFilename());
            blogpostDTO.setBlogpost_coulda_shoulda_album_art_2(S3Util.S3_URL + prefix + blogpostCouldaAlbumArt2.getOriginalFilename());
            blogpostDTO.setBlogpost_map(S3Util.S3_URL + prefix + blogpostMap.getOriginalFilename());
            String date = new Date().toString();
            blogpostDTO.setCreatedAt(date);
            blogpostDTO.setUpdatedAt(date);

            blogpostService.save(ObjectMapperUtils.map(blogpostDTO, Blogpost.class));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Files Successfully Uploaded");
        } catch (AwsServiceException | SdkClientException | IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    private AlbumArtDetails getAlbumArtDetails(String albumArtDetailString){
            Gson gson = new Gson();
            AlbumArtDetails albumArtDetails = gson.fromJson(albumArtDetailString, AlbumArtDetails.class);
            return albumArtDetails;
    }

    private MapDetails getMapDetails(String mapDetailsString){
        Gson gson = new Gson();
        MapDetails mapDetails = gson.fromJson(mapDetailsString, MapDetails.class);
        return mapDetails;
    }

    private CouldaShouldaDetails getCouldaShouldaDetails(String couldaShouldaString){
        Gson gson = new Gson();
        CouldaShouldaDetails couldaShouldaDetails = gson.fromJson(couldaShouldaString, CouldaShouldaDetails.class);
        return couldaShouldaDetails;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable("id") String id, @RequestBody BlogpostDTO blogpostDTO){

        try{
            blogpostService.updateBlog(ObjectMapperUtils.map(blogpostDTO, Blogpost.class), id);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
