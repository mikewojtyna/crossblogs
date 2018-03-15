package com.crossover.techtrial;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import com.crossover.techtrial.dto.ArticlesDTO;
import com.crossover.techtrial.dto.CommentsDTO;
import com.crossover.techtrial.model.Article;
import com.google.common.reflect.TypeToken;

@SpringBootApplication
@EnableAutoConfiguration
public class CrossBlogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrossBlogsApplication.class, args);
	}
	
	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		TypeMap<Page, ArticlesDTO> typeMap = modelMapper.createTypeMap(Page.class,ArticlesDTO.class);
		typeMap.addMapping(src -> src.getContent(), (dest, v) -> dest.setArticles((List<Article>) v));
		typeMap.addMapping(Page::getTotalPages, ArticlesDTO::setPageCount);
		typeMap.addMapping(Page::getNumber, ArticlesDTO::setPageNumber);
		typeMap.addMapping(Page::getSize, ArticlesDTO::setPageSize);
		
		TypeMap<Page,CommentsDTO> typeMap2 = modelMapper.createTypeMap( Page.class,CommentsDTO.class);
		//typeMap2.addMappings(Page::getContent, CommentsDTO::setComments);
		typeMap2.addMapping(Page::getTotalPages, CommentsDTO::setPageCount);
		typeMap2.addMapping(Page::getNumber, CommentsDTO::setPageNumber);
		typeMap2.addMapping(Page::getSize, CommentsDTO::setPageSize);
		
		return modelMapper;
	}
}
