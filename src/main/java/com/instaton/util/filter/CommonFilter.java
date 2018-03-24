package com.instaton.util.filter;

import java.util.List;

import com.instaton.entity.enums.BlackNameOperationEnum;
import com.instaton.entity.social.BlackNameEntity;
import com.instaton.util.TurkishUtils;

public class CommonFilter {

  public static boolean checkIfBlackFullname(
      final String fullname, final List<BlackNameEntity> blackNameEntityList) {

    final String firstName = fullname.split(" ")[0];

    for (final BlackNameEntity blackNameEntity : blackNameEntityList) {
      final String name = blackNameEntity.getName();

      if (blackNameEntity.getOperation() == BlackNameOperationEnum.EQUALS
          && TurkishUtils.equalsIgnoreCase(firstName, name)) {
        return true;
      }
      if (blackNameEntity.getOperation() == BlackNameOperationEnum.STARTSWITH
          && TurkishUtils.startsWithIgnoreCase(firstName, name)) {
        return true;
      }
      if (blackNameEntity.getOperation() == BlackNameOperationEnum.CONTAINS
          && TurkishUtils.containsIgnoreCase(fullname, name)) {
        return true;
      }
    }
    return false;
  }

  private CommonFilter() {
    throw new IllegalAccessError();
  }
}
