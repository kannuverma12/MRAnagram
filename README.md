# MRAnagram

This is spring boot application using hadoop and map-reduce to find whether the two given strings are anagrams or not.

It uses Map reduce program to check if the two strings are anagram or not.

It contains the main class Anagram.java which load the hadoop configuration and creates a hadoop job and configures the map and reduce programs to execute the respective job.

Map Program reads a file containing a list of strings.
Reduce program return a file containing the respective string and its possible anagrams.

Project is a spring boot program using Hadoop 2.7.2 version
