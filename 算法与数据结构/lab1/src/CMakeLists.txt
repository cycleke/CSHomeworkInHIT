cmake_minimum_required(VERSION 3.13)
project(Lab1
  VERSION 0.0.1)

set(APP_NAME "eval")

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_INCLUDE_CURRENT_DIR ON)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
set(CMAKE_CXX_FLAGS "-g")


include_directories(
  ${CMAKE_CURRENT_LIST_DIR}/lib
  )

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ./build)
set(SOURCE_FILES
  main.cpp
  lib/number.h
  lib/number.cpp
  )

add_executable(${APP_NAME} ${SOURCE_FILES})
target_link_libraries(${APP_NAME}
  stdc++fs
  )
