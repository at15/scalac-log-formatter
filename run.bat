@echo off
echo "run jar"
scala target\scala-2.11\scalac-log-formatter_2.11-1.0.jar -logSrc:logs/linter-me-package.log -logEncoding:GBK -dist:custom-dist