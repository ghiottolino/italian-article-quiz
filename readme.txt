INSTRUCTIONS FOR BUILDING

cd /home/tex/android-sdks/platform-tools

jarsigner -verbose -keystore android.keystore italian-article-quiz.apk android

/home/tex/android-sdks/tools/zipalign -v 4 /home/tex/android-sdks/platform-tools/italian-article-quiz.apk /home/tex/android-sdks/platform-tools/ItalianArticlesQuiz.apk
 
rm italian-article-quiz.apk

MOVING TO WEBSERVER

sudo rm /var/www/android/ItalianArticlesQuiz.apk

sudo mv ItalianArticlesQuiz.apk /var/www/android/ItalianArticlesQuiz.apk

ls
