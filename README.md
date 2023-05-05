# テンプレ利用の仕方


# テンプレートを利用してプロジェクトを作る

## Build成功まで
1. まずこのプロジェクトをクローンする
1. (ただの実験なら省いても可) クローン後、フォルダ名(RobotCodeTemplate)を適当なものに変更
1. IDEでそのフォルダを開き、`rm -r .git/ && rmdir .git`を実行
1. build.gradleを右クリックし、`Build Robot Code`を実行、`BUILD SUCCESSFUL`を確認


## Git初期化
1. `git init`を実行
1. `git add . && git commit -m "initial commit"`を実行し、とりあえず現状をコミット
1. `git remote add origin (リモートリポジトリのURL)`でリモートリポジトリを追加

