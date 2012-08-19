INSTRUCTIONS FOR BUILDING

cd /home/tex/android-sdks/platform-tools

mv Italian\ Gender\ Quiz.apk Italian-Gender-Quiz.apk

jarsigner -verbose -keystore android.keystore  Italian-Gender-Quiz.apk android

/home/tex/android-sdks/tools/zipalign -v 4 /home/tex/android-sdks/platform-tools/Italian-Gender-Quiz.apk /home/tex/android-sdks/platform-tools/ItalianGenderQuiz.apk
 
rm Italian-Gender-Quiz.apk

sudo rm /var/www/android/ItalianGenderQuiz.apkapk

sudo mv ItalianGenderQuiz.apk /var/www/android/ItalianGenderQuiz.apk

ls
