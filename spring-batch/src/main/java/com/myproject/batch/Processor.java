package com.myproject.batch;

import com.myproject.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<User, User> {

  private static final Map<String, String> DEPT_NAMES = new HashMap<>();

  public Processor() {
    DEPT_NAMES.put("Product Management", "001");
    DEPT_NAMES.put("Engineering", "002");
    DEPT_NAMES.put("Legal", "003");
    DEPT_NAMES.put("Training", "004");
  }

  @Override
  public User process(User user) throws Exception {
    Optional<String> optionalDept = Optional.ofNullable(DEPT_NAMES.get(user.getDepartment()));
    optionalDept.ifPresent(user::setDepartment);
    return user;
  }
}
