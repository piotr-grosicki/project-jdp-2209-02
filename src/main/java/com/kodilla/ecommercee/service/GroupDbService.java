package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupDbService {

    @Autowired
    private GroupRepository groupRepository;

    public Group getGroup(long groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

}