package org.yg.practice.flyway;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import jdk.jshell.SourceCodeAnalysis.Documentation;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
// import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import org.springframework.restdocs.RestDocumentationExtension;

import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import com.epages.restdocs.apispec.ResourceSnippetParameters;



//@RunWith(SpringRunner.class)//Junit5 에서는 ExtensionWith로 대체 되었는데 @SpringBootTest가 기본적으로 포함
//@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class UserApiDoc {
    
    // JUnit4 style
    //@Rule
    // public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    // @Autowired
    // WebApplicationContext context;
    //org.springframework.test.web.servlet
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext context, RestDocumentationContextProvider restDocumentation){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(documentationConfiguration(restDocumentation)) // documentationConfiguration 가 resDocumentation 의 인스턴스를 얻게 해줌 
            .build();
        System.out.println("before ~~~~~~~~~");
    }

    @Test
    //org.springframework.restdocs.request.RequestDocumentation
    //org.springframework.restdocs.payload.PayloadDocumentation
    public void testRead() throws Exception{
        this.mockMvc.perform(get("/api/user/{id}", 1))
            .andDo(print())
            .andDo(document("user",
                pathParameters(
                    parameterWithName("id").description("user id")
                ),
                responseFields(
                    fieldWithPath("resultCode").description("response code"),
                    fieldWithPath("data.id").description("id"),
                    fieldWithPath("data.account").description("account"),
                    fieldWithPath("data.email").description("email"),
                    fieldWithPath("data.phoneNumber").description("phone number"),
                    fieldWithPath("data.createdAt").description("create"),
                    fieldWithPath("data.updatedAt").description("modify 시간")
                )
            ));

        this.mockMvc.perform(get("/api/user/{id}", 1))
            .andDo(document("user",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder().description("User Information CURD").summary("User Information")
                    .pathParameters(
                        parameterWithName("id").description("user id")
                    )
                    .responseFields(
                        fieldWithPath("resultCode").description("response code"),
                        fieldWithPath("data.id").description("id"),
                        fieldWithPath("data.account").description("account"),
                        fieldWithPath("data.email").description("email"),
                        fieldWithPath("data.phoneNumber").description("phone number"),
                        fieldWithPath("data.createdAt").description("create"),
                        fieldWithPath("data.updatedAt").description("modify 시간")
                    ).build()
                )
            ));
    }
}
