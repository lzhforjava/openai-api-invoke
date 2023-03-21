# 介绍
- - -
强调一下，作者自己是免费用户，因为用免费api-key调用一直connect time out，[这里记录一下解决办法](https://github.com/noobnooc/noobnooc/discussions/9)。  
**如果你有别的解决办法可以联系我，或者你有好的交流群可以拉我一下，互相学习**  
![weixin](https://user-images.githubusercontent.com/64308460/226585700-80f885cc-f980-4c1d-964b-eaa8ed63cc8b.png)
[提示：作者购买的阿里云域名](https://wanwang.aliyun.com/domain/searchresult/#/?keyword=openai&suffix=.com)映射到[cloudflare](https://dash.cloudflare.com/)
***************
## 项目介绍
- - - 
本项目使用java语言，使用SpringBoot Web框架制作的一个小demo。  
如果通过maven创建项目，可通过官方依赖快速开发，[官方项目链接](https://github.com/TheoKanning/openai-java)
```maven
   <dependency>
    <groupId>com.theokanning.openai-gpt3-java</groupId>
    <artifactId>{api|client|service}</artifactId>
    <version>version</version>       
   </dependency>
```
*******
## 启动该项目
- - -
+ 本项目由于免费api-key connect time out，所以没有导入依赖，而是将项目源码拿了过来稍作修改。
+ [OpenAiService中的BASE_URL修改为自己的代理域名](https://github.com/lzhforjava/openai-api-invoke/blob/master/src/main/java/com/lzh/chatgpt/service/OpenAiService.java)；
+ [ChatController中](https://github.com/lzhforjava/openai-api-invoke/blob/master/src/main/java/com/lzh/chatgpt/controller/ChatController.java)new OpenAiService("sk-********************************************************")的api-key替换为自己的；
+ 项目启动类[OpenaiApiInvokeApplication.java](https://github.com/lzhforjava/openai-api-invoke/blob/master/src/main/java/com/lzh/chatgpt/OpenaiApiInvokeApplication.java)；
+ 如果你未修改端口的话，该项目访问地址为localhost:8082
************************************************************************************************************
## 页面
- - -
![project](https://user-images.githubusercontent.com/64308460/226585747-058f62c3-afd8-40a6-b6c6-eedd9a5ee86d.png)
html页面很low，可以实现简单的文本发送，点击send按钮发送文本，本例中未实现回车事件。
************************************************************************************************************
### 简单案例
导入okhttp依赖
```maven
 <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>4.2.0</version>
        </dependency>
```
```java
package com.chatgpt.openai.service;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author lzh
 * @Date 2023 03 18 09 15
 * @DESC
 **/
public class CompletionTest {


    String token =  "sk-********************************";
    OpenAiService service = new OpenAiService(token, Duration.ofMinutes(5));


    public static void main(String[] args) throws IOException {
        String url = "https://自己的域名/v1/completions";
        String apiKey = "sk-*************************************";
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(6000, TimeUnit.SECONDS)
                .writeTimeout(6000,TimeUnit.SECONDS)
                .readTimeout(6000,TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,"{\n" +
                "\"model\":\"text-davinci-003\",\n" +
                "\"max_tokens\":2048,\n" +
                "\"top_p\":1,\n" +
                "\"temperature\":0.5,\n" +
                "\"frequency_penalty\":0,\n" +
                "\"presence_penalty\":0,\n" +
                "\"prompt\":\"openai api 基于completions这个接口写个http调用的java代码\"\n" +
                "}");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        String answer = responseJson.split("\"text\":\"")[1].split("\"")[0];
        System.out.println(answer);
    }

    void createCompletion() {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("ada")
                .prompt("你好")
                .echo(true)
                .n(5)
                .maxTokens(50)
                .user("testing")
                .logitBias(new HashMap<>())
                .logprobs(5)
                .build();

        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        System.out.println(choices);
//        Assertions
//        assertEquals(5, choices.size());
//        assertNotNull(choices.get(0).getLogprobs());
    }
}

```


