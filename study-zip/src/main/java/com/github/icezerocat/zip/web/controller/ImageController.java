package com.github.icezerocat.zip.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.icezerocat.zip.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 图片控制器
 * CreateDate:  2021/4/16 13:55
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping
public class ImageController {

    @PostMapping(value = "/uploadBlobImg")
    @ResponseBody
    public JSONObject uploadFileImg(@RequestParam("file") MultipartFile blobFile) {
        log.info("对象：{}", blobFile);
        return UploadUtil.save("/uploads/image/", blobFile);
    }
}
