package com.pluralsight.conferencedemo.contoller;

import com.pluralsight.conferencedemo.model.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
  @Autowired
  SpeakerRepository speakerRepository;

  @GetMapping
  public List<Speaker> list() {
    return speakerRepository.findAll();
  }

  @GetMapping("{id}")
  public Speaker getSpeaker(@PathVariable Long id) {
    return speakerRepository.getOne(id);
  }

  @PostMapping
  public Speaker create(@RequestBody final Speaker speaker) {
    return speakerRepository.saveAndFlush(speaker);
  }

  @DeleteMapping(value = "{id}")
  public void delete(@PathVariable Long id){
    speakerRepository.deleteById(id);
  }

  @PutMapping(value = "{id}" )
  public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
    Speaker existingSpeaker = speakerRepository.getOne(id);
    BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
    return speakerRepository.saveAndFlush(existingSpeaker);
  }

}
