# CSYE7200_FinalProject
Repo for CSYE7200 Big Data Using Scala Final Project 
Project: Dog Breed Identification
*Group 10: Xin Wen, Shaowen Cui*


### Goals
● This app aims at helping users to identify dog’s breed based on their uploaded image;  
● System would list the top three most possible breeds for that dog, according to dog’s face inside image;  
● System could also detect human faces within the images and mark them out;  
● This system would be useful for classifying photos into categories based on different breeds of dog.


### Use Case
#####General users:
By uploading image to app, they can:  
● Identify Dog's Breed: app will help identify image with dog and give Top 3 guesses of dog's breed along with probability.  
● Recognize Human Face: app can also mark all human faces inside the image.
related code: core/src/main/scala/csye_7200/example/SingleImgProcess

#####Business users:  
● By sending large amount of photos, they can classify images: app can differentiate images according to dog’s breed and classify the images into categories.
related code: core/src/main/scala/csye_7200/folder/ImportFolder 
&& core/src/main/scala/csye_7200/calculation/PrecisionCalculation

### Methodology
● Data Preprocessing: OpenCV -- Mat, Grey Scale, Equalize Histogram  
● Model Re-Training: CNN (Convolutional Neural Network) based on Tensorflow
● Dog Breed Identification: CNN

### Reference
● https://github.com/lloydmeta/scala-akka-cv-part1
● https://github.com/mskimm/tensorflow-scala
● https://github.com/googlecodelabs/tensorflow-for-poets-2

[![CircleCI](https://circleci.com/gh/cicioutofspace/CSYE7200_FinalProject/tree/master.svg?style=svg)](https://circleci.com/gh/cicioutofspace/CSYE7200_FinalProject/tree/master)
