package com.example.ixithon.util;

import android.content.Context;

import java.util.Random;

public class AppUtil {
  public static long generateUniqueId() {
    Random random = new Random();
    random.setSeed(System.currentTimeMillis());
    return random.nextLong();
  }
}
