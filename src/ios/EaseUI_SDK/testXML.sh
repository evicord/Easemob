#!/bin/sh
# Shell script to find out all the files under a directory and
#its subdirectories. This also takes into consideration those files
#or directories which have spaces or newlines in their names

DIR="."

str_behind="\" />"
str_frameworkBehind="\" custom=\"true\" />"
str_sourcefileBehind="\" framework=\"true\" />"

function list_files()
{
if !(test -d "$1")
then echo $1; return;
fi

cd "$1"
#echo;
#echo `pwd`:; #Display Directory name


for i in *
do
fileName="$i"
extension="${fileName##*.}"
str_center=`pwd`/"$i"

case $extension in
'framework' )
str_front="<framework src=\""
echo ${str_front}${str_center}${str_frameworkBehind}
;;
'bundle'|'xib'|'png'|'jpg'|'jpeg'|'gif'|'bmp'|'pch'|'strings')
str_front="<resource-file src=\""
echo ${str_front}${str_center}${str_behind}
;;
'h' )
str_front="<header-file src=\""
echo ${str_front}${str_center}${str_behind}
;;
'm'|'mm'|'c' )
str_front="<source-file src=\""
echo ${str_front}${str_center}${str_behind}
;;
'a')
str_front="<source-file src=\""
echo ${str_front}${str_center}${str_sourcefileBehind}
;;
*  )
if test -d "$i" #if dictionary
then
list_files "$i" #recursively list files
cd ..
else
echo "OtherType File":"$i"
fi
;;
esac

done
}

if [ $# -eq 0 ]
then list_files .
exit 0
fi

for i in $*
do
DIR="$1"
list_files "$DIR"
shift 1 #To read next directory/file name
done