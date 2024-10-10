# 使用 Gradle 官方映像作為構建基礎
FROM gradle:7.6-jdk17 AS build

# 設定工作目錄
WORKDIR /app

# 複製所有必要文件
COPY build.gradle settings.gradle ./
COPY gradle gradle/
COPY src src/
COPY gradlew ./

# 確保 gradlew 是可執行的
RUN chmod +x gradlew

# 確保本地安裝的 Gradle 能夠正常工作
RUN ./gradlew --version

# 建構專案
RUN ./gradlew build --no-daemon

# 使用較小的 JRE 映像來運行應用
FROM openjdk:17-jdk-slim

# 設定工作目錄
WORKDIR /app

# 從建構階段複製可執行的 JAR 檔案
COPY --from=build /app/build/libs/*.jar app.jar

# 開放應用埠
EXPOSE 8080

# 定義啟動命令
ENTRYPOINT ["java", "-jar", "app.jar"]
