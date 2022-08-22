package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.dto.GroupsDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/groups")
public class GroupController {

    @GetMapping
    public List<GroupsDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{groupId}")
    public GroupsDto getGroupById(@PathVariable Long groupId) {
        return new GroupsDto(1L,"group name","group description");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupsDto groupDto) {

    }

    @PutMapping
    public GroupsDto updateGroup(@RequestBody GroupsDto groupDto) {
        return new GroupsDto(1L,"Updated group name","Updated group description");
    }
}
