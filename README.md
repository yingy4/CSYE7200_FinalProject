# CSYE7200_FinalProject
Repo for CSYE7200 Big Data Using Scala Final Project  
*Group 10: Xin Wen, Shaowen Cui*


### Goals
● This app aims at helping users to identify dog’s breed based on their uploaded image;  
● System would list the top three most possible breeds for that dog, according to dog’s face inside image;  
● System could also detect human faces within the images and mark them out;  
● This system would be useful for classifying photos into categories based on
different breeds of dog.


### Use Case
#####General users:
By uploading image to app, they can:  
● Identify Dog's Breed: app will help identify image with dog and give Top 3 guesses of dog's breed along with probability.  
● Recognize Human Face: app can also mark all human faces inside the image.

#####Business users:  
By sending large amount of photos, they can classify images: app can differentiate images with dog from those without and classify the images into categories according to dog’s breed.


###Methodology
● Data Preprocessing: OpenCV -- Mat, Grey Scale, Equalize Histogram  
● Facial Detection: Facial Keypoint Localization  
● Re-Training: CNN (Convolutional Neural Network) based on Tensorflow



[![CircleCI](https://circleci.com/gh/cicioutofspace/CSYE7200_FinalProject/tree/master.svg?style=svg)](https://circleci.com/gh/cicioutofspace/CSYE7200_FinalProject/tree/master)
