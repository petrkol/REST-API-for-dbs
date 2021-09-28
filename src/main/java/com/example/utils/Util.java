package com.example.utils;

public class Util {

  private Util() {}

  /**
   * Tries to convert min and max as string to integer and then calculate median
   * If conversion is not supported return a null
   * 
   * @param min min value doesn't have to be an integer
   * @param max max value doesn't have to be an integer
   * @return null if conversion to int not supported, otherwise median
   */
  public static Integer calculateMedian(String min, String max) {
    Integer median;

    try {
      Integer minInt = Integer.parseInt(min);
      Integer maxInt = Integer.parseInt(max);
      median = (minInt + maxInt) / 2;

    } catch (NumberFormatException ex) {
      median = null;
    }

    return median;

  }

}
