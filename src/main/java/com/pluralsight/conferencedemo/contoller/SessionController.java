package com.pluralsight.conferencedemo.contoller;

import com.pluralsight.conferencedemo.model.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

  @Autowired
  SessionRepository sessionRepository;

  @GetMapping
  public List<Session> list() {
    return sessionRepository.findAll();
  }

  @GetMapping
  @RequestMapping("/{id}")
  public Session get(@PathVariable Long id) {
    return sessionRepository.getOne(id);
  }

  @PostMapping
  public Session create(@RequestBody final Session session) {
    return sessionRepository.saveAndFlush(session);
  }

  @DeleteMapping(value = "{id}")
  public void delete(@PathVariable Long id) {
    // also need to check for children records before deleting
    sessionRepository.deleteById(id);
  }

  @PutMapping(value = "{id}")
  public Session update(@PathVariable Long id, @RequestBody Session session) {
    // because this is a put, we expect all attributes to be passed in.  A PATCH would only need what's changing
    // TODO Add validation that all attributes are passed in, otherwise return a 400 payload
    Session existingSession = sessionRepository.getOne(id);
    BeanUtils.copyProperties(session, existingSession, "session_id");
    return sessionRepository.saveAndFlush(existingSession);
  }


}
