# 使用官方的 OpenJDK 作為基礎映像
FROM openjdk:21-jdk-slim AS build

# 設定工作目錄
WORKDIR /app

# 複製 Gradle 配置檔案
COPY build.gradle settings.gradle ./
COPY gradle gradle

# 複製源代碼
COPY src src

# 建構專案
RUN ./gradlew build --no-daemon

# 使用一個較小的 JRE 映像來運行應用
FROM openjdk:21-jre-slim

# 設定工作目錄
WORKDIR /app

# 從建構階段複製可執行的 JAR 檔案
COPY --from=build /app/build/libs/*.jar app.jar

# 開放應用埠
EXPOSE 8080

# 定義啟動命令
ENTRYPOINT ["java", "-jar", "app.jar"]
