package com.kodilla.ecommercee.service;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupDbService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        System.out.println("getAllGroups");
        return groupRepository.findAll();
    }

    public Group getGroupById(long groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public Group updateGroup(Group group) throws GroupNotFoundException {
        Group groupToUpdate = getGroupById(group.getId());

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
