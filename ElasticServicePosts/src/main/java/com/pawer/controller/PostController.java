package com.pawer.controller;


import com.pawer.dto.request.BaseRequestDto;
import com.pawer.dto.request.CommentToPostRequestDto;
import com.pawer.dto.request.PostSaveRequestDto;
import com.pawer.dto.response.BaseResponseDto;
import com.pawer.dto.response.CommentToPostResponse;
import com.pawer.dto.response.PostFindAllResponse;
import com.pawer.repository.entity.Post;
import com.pawer.service.CommentToPostService;
import com.pawer.service.FavToPostService;
import com.pawer.service.LikeToPostService;
import com.pawer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final LikeToPostService likeToPostService;
    private final CommentToPostService commentToPostService;
    private final FavToPostService favToPostService;


    @PostMapping("/getalldata")
    private ResponseEntity<Void> getAllData(@RequestBody PostSaveRequestDto dto){
        System.out.println("Furkan buraya sout at dedi");
        postService.getAllDataFromPost(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/findallpage")
    @CrossOrigin("*")
    public ResponseEntity<Page<PostFindAllResponse>> findallPage(@RequestBody BaseResponseDto dto,
                                                                 @RequestParam(defaultValue = "10")Integer pageSize,
                                                                 @RequestParam(defaultValue = "0") int pageNumber,
                                                                 @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                 @RequestParam(defaultValue = "createDate") String sortParameter){


        return ResponseEntity.ok(postService.findAllPosts(dto.getToken(),pageSize,pageNumber,direction,sortParameter));
    }
    /*
    @GetMapping("/findallpage")
    @CrossOrigin("*")
    public ResponseEntity<Page<Post>> findallPage(@RequestParam(defaultValue = "10")Integer pageSize,
                                                  @RequestParam(defaultValue = "0")   int pageNumber,
                                                  @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                  @RequestParam(defaultValue = "createDate") String sortParameter){

        return ResponseEntity.ok(postService.findAll(pageSize,pageNumber,direction,sortParameter));
    }

*/

    @PostMapping("/createlikepost")
    @CrossOrigin("*")
    public ResponseEntity<Void> createLikePost(@RequestBody BaseRequestDto dto){
        likeToPostService.createLikePost(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping ("/findallmypost")
    @CrossOrigin("*")
    public ResponseEntity<Page<Post>> findMyPosts(@RequestBody BaseResponseDto dto,
                                                  @RequestParam(defaultValue = "10")Integer pageSize,
                                                  @RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                  @RequestParam(defaultValue = "createDate") String sortParameter){
        return ResponseEntity.ok(postService.myPost(dto.getToken(),pageSize,pageNumber,direction,sortParameter));
    }

    @GetMapping("/findallcomment")
    @CrossOrigin("*")
    @ResponseBody
    public ResponseEntity<List<CommentToPostResponse>> getCommentList(BaseRequestDto dto){
        return ResponseEntity.ok(commentToPostService.findAllComment(dto));
    }

    @PostMapping("/likepost")
    @CrossOrigin("*")
    @ResponseBody
    public ResponseEntity<Integer> getLikeCount(BaseRequestDto dto){
        return ResponseEntity.ok(likeToPostService.likePostCount(dto));
    }



    @PostMapping("/createfavpost")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createFavPost(@RequestBody BaseRequestDto dto){
        return ResponseEntity.ok(favToPostService.createFavPost(dto));
    }

    @PostMapping("/myfavtopostlist")
    @CrossOrigin("*")
    public ResponseEntity<List<PostFindAllResponse>> myFavPostList(@RequestBody BaseRequestDto dto){
        return ResponseEntity.ok(favToPostService.myFavPostList(dto));
    }

    @PostMapping("/createcommenttopost")
    @CrossOrigin("*")
    public ResponseEntity<Void> createCommentToPost(@RequestBody CommentToPostRequestDto dto){
        commentToPostService.createCommentToPost(dto);
        return ResponseEntity.ok().build();
    }




}







