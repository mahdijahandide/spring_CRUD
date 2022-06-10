package ir.mhdi.portfolio.controller;

import ir.mhdi.portfolio.exception.ResourceNotFoundException;
import ir.mhdi.portfolio.model.Skills;
import ir.mhdi.portfolio.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/skills")
public class SkillsController {

    @Autowired
    private SkillsRepository skillsRepository;

    //get all skills
    @GetMapping
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

    //create new skill
    @PostMapping
    public Skills createSkills(Skills skills) {
        return skillsRepository.save(skills);
    }

    //get skill by ID
    @GetMapping("{id}")
    public ResponseEntity<Skills> getSkillById(@PathVariable Long id) {
        Skills skills = skillsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("skill not exist with id: " + id));
        return ResponseEntity.ok(skills);
    }

    //update skill
    @PutMapping("{id}")
    public ResponseEntity<Skills> updateSkill(@PathVariable long id, @RequestBody Skills skills) {
        Skills updateSkill = skillsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("skill not exist with id: " + id));

        updateSkill.setName(skills.getName());
        updateSkill.setDesc(skills.getDesc());

        skillsRepository.save(updateSkill);
        return ResponseEntity.ok(updateSkill);
    }

    //delete skill
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>deleteSkill(@PathVariable long id){
        Skills skills=skillsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("skill not exist with id: "+id));

        skillsRepository.delete(skills);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
