@echo off
echo "run jar"
scala target\scala-2.11\scalac-log-formatter_2.11-1.1.jar -logSrc:logs/unused_param.log -logEncoding:UTF-8 -dist:custom-dist