package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupDbService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(long groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public Group updateGroup(Group group) throws GroupNotFoundException {

        Group groupToUpdate = groupRepository.findById(group.getId()).orElseThrow(GroupNotFoundException::new);
        if (group.getName() != null) {
            groupToUpdate.setName(group.getName());
        }
        if (group.getDescription() != null) {
            groupToUpdate.setDescription(group.getDescription());
        }
        saveGroup(groupToUpdate);
        return groupToUpdate;
    }
}