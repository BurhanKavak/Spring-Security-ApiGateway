package com.example.taskservice.feign;

import com.example.taskservice.dto.ProjectIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROJECT-SERVICE")
public interface ProjectFeignClient {


    @GetMapping("project/{id}")
    ProjectIdResponse getProjectById(@PathVariable Long id);

}
