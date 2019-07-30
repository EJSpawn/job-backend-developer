package com.project.jobbackenddeveloper.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.jobbackenddeveloper.model.News;
import com.project.jobbackenddeveloper.util.JobBackendDeveloperConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NewsResourceTest {
	@Autowired
	private MockMvc mockMvc;	
	private News news;
	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Before
	public void init() {
		news = new News();
		news.setDescription("Artigo 11");
	}
	
	@Test
	public void getNewsList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get(JobBackendDeveloperConstants.RESOURCE_INTELIPOST_V1_NEWS)
				.accept(MediaType.ALL))
				.andExpect(status().isOk());
	}
	
	@Test
	public void get() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get(JobBackendDeveloperConstants.RESOURCE_INTELIPOST_V1_NEWS + "/1")
				.accept(MediaType.ALL))
				.andExpect(status().isOk());
	}
	
	@Test
	public void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(JobBackendDeveloperConstants.RESOURCE_INTELIPOST_V1_NEWS+"/10")
				.accept(MediaType.ALL)).andExpect(status().isOk());
	}
	
	@Test
	public void create() throws Exception {		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
	    ObjectWriter ow = mapper.writer().forType(News.class);
	    String requestJson=ow.writeValueAsString(news);
	    
		mockMvc.perform(MockMvcRequestBuilders.post(JobBackendDeveloperConstants.RESOURCE_INTELIPOST_V1_NEWS)
				.contentType(APPLICATION_JSON_UTF8)
				.content(requestJson)
				.accept(MediaType.ALL)).andExpect(status().isOk());
	}
	
	@Test
	public void update() throws Exception {
		Long id = 2L; 
		news.setId(id);
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().forType(News.class);
	    String requestJson=ow.writeValueAsString(news);
	    
		mockMvc.perform(MockMvcRequestBuilders.put(JobBackendDeveloperConstants.RESOURCE_INTELIPOST_V1_NEWS + "/" + id)
				.contentType(APPLICATION_JSON_UTF8)
				.content(requestJson)
				.accept(MediaType.ALL)).andExpect(status().isOk());
	}
}
