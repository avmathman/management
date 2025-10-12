# ---- Build Stage ----
FROM gradle:7.6.3-jdk8 as builder

# Set working directory
WORKDIR /usr/app

# Copy only necessary files first (for caching)
COPY build.gradle settings.gradle* gradle.properties* /usr/app/

# Copy source code
COPY src /usr/app/src

# Run Gradle build (no daemon, no tests)
RUN gradle build -x test --no-daemon

# ---- Runtime Stage ----
FROM openjdk:8u102-jdk

# Create app directory
WORKDIR /usr/app

# Copy the built jar from the builder stage
COPY --from=builder /usr/app/build/libs/*.jar management.jar

# Set the entrypoint
ENTRYPOINT ["java","-jar","/usr/app/management.jar"]
