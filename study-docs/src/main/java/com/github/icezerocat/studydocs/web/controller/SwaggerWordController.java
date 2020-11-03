package com.github.icezerocat.studydocs.web.controller;

import com.github.icezerocat.studydocs.service.SwaggerWordService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Description: swagger转word控制器
 * CreateDate:  2020/10/23 14:19
 *
 * @author zero
 * @version 1.0
 */
@Api(tags = "swagger转word控制器")
@Controller
public class SwaggerWordController {

    @Value("${swagger.url:}")
    private String swaggerUrl;
    @Value("${swagger.wordName:api}")
    private String wordName;
    @Value("${swagger.htpType:http://}")
    private String httpType;

    @Resource
    private HttpServletResponse response;
    private final SwaggerWordService swaggerWordService;
    private final SpringTemplateEngine springTemplateEngine;

    public SwaggerWordController(SwaggerWordService swaggerWordService, SpringTemplateEngine springTemplateEngine) {
        this.swaggerWordService = swaggerWordService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping("say")
    public String say() {
        return "index";
    }

    @GetMapping("say1")
    public String say1() {
        return "templates/hello";
    }

    @GetMapping("say2")
    public String say2() {
        return "jsp/hello";
    }

    /**
     * 将 swagger 文档转换成 html 文档，可通过在网页上右键另存为 xxx.doc 的方式转换为 word 文档
     *
     * @param model    模式
     * @param url      需要转换成 word 文档的资源地址
     * @param download 是否下载
     * @return html在线文档
     */
    @Deprecated
    @ApiOperation(value = "将 swagger 文档转换成 html 文档，可通过在网页上右键另存为 xxx.doc 的方式转换为 word 文档", response = String.class, tags = {"Word"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功。", response = String.class)})
    @GetMapping(value = "/toWord")
    public String getWord(
            Model model,
            @ApiParam(value = "资源地址") @RequestParam(value = "url", required = false) String url,
            @ApiParam(value = "是否下载") @RequestParam(value = "download", required = false, defaultValue = "1") Integer download) {
        generateModelData(model, url, download);
        return "word";
    }

    /**
     * 将 swagger 文档一键下载为 doc 文档
     *
     * @param model 模式
     * @param url   需要转换成 word 文档的资源地址
     */
    @ApiOperation(value = "将 swagger 文档一键下载为 doc 文档", tags = {"Word"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功。")})
    @GetMapping(value = "/downloadWord")
    public void word(Model model, @ApiParam(value = "资源地址") @RequestParam(required = false) String url) {
        generateModelData(model, url, 0);
        writeContentToResponse(model);
    }

    /**
     * 将 swagger json文件转换成 word文档并下载
     *
     * @param model    模式
     * @param jsonFile 需要转换成 word 文档的swagger json文件
     */
    @ApiOperation(value = "将 swagger json文件转换成 word文档并下载", tags = {"Word"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功。")})
    @PostMapping(value = "/fileToWord")
    public void getWord(Model model, @ApiParam("swagger json file") @Valid @RequestPart("jsonFile") MultipartFile jsonFile) {
        generateModelData(model, jsonFile);
        writeContentToResponse(model);
    }

    /**
     * 将 swagger json字符串转换成 word文档并下载
     *
     * @param model   模式
     * @param jsonStr 需要转换成 word 文档的swagger json字符串
     */
    @ApiOperation(value = "将 swagger json字符串转换成 word文档并下载", tags = {"Word"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功。")})
    @PostMapping(value = "/strToWord")
    public void getWord(Model model, @ApiParam("swagger json string") @Valid @RequestParam("jsonStr") String jsonStr) {
        generateModelData(model, jsonStr);
        writeContentToResponse(model);
    }

    /**
     * 生成模板数据
     *
     * @param model    模式
     * @param url      资源地址
     * @param download 是否下载
     */
    private void generateModelData(Model model, String url, Integer download) {
        url = StringUtils.defaultIfBlank(url, swaggerUrl);
        Map<String, Object> result = this.swaggerWordService.tableList(url);
        model.addAttribute("url", url);
        model.addAttribute("download", download);
        model.addAllAttributes(result);
    }

    /**
     * 生成模板数据
     *
     * @param model   模式
     * @param jsonStr json文档数据
     */
    private void generateModelData(Model model, String jsonStr) {
        Map<String, Object> result = this.swaggerWordService.tableListFromString(jsonStr);
        model.addAttribute("url", this.httpType);
        model.addAttribute("download", 0);
        model.addAllAttributes(result);
    }

    /**
     * 生成模板数据
     *
     * @param model    模式
     * @param jsonFile json文档文件
     */
    private void generateModelData(Model model, MultipartFile jsonFile) {
        Map<String, Object> result = this.swaggerWordService.tableList(jsonFile);
        String originalFilename = jsonFile.getOriginalFilename();

        if (StringUtils.isNotBlank(originalFilename)) {
            this.wordName = originalFilename.replaceAll(".json", "");
        }

        model.addAttribute("url", this.httpType);
        model.addAttribute("download", 0);
        model.addAllAttributes(result);
    }

    /**
     * 将内容写入响应，提供下载
     */
    private void writeContentToResponse(Model model) {
        Context context = new Context();
        context.setVariables(model.asMap());
        String content = this.springTemplateEngine.process("word", context);
        this.response.setContentType("application/octet-stream;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        try (BufferedOutputStream bos = new BufferedOutputStream(this.response.getOutputStream())) {
            this.response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(this.wordName + ".doc", "utf-8"));
            byte[] bytes = content.getBytes();
            bos.write(bytes, 0, bytes.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
