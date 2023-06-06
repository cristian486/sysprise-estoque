FROM eclipse-temurin:17-alpine
ENV SPRING_PROFILES_ACTIVE=prod
ENV DATABASE_URL=jdbc:mysql://sysprise_database:3306/sysprise_estoque
ENV DATABASE_USER=root
ENV DATABASE_PASSWORD=f05313181ae0ef5ba1df08d08e0f49de0eecfcdf02e5af371ecc538d84fee67a
COPY target/sysprise-estoque.jar .
ENTRYPOINT [ "java", "-jar", "sysprise-estoque.jar" ]