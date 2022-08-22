package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{groupId}")
    public GroupDto getGroupById(@PathVariable Long groupId) {
        return new GroupDto(1L,"group name","group description");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {

    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(1L,"Updated group name","Updated group description");
    }
}
