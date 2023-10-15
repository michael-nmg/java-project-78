.PHONY: build

test:
	@./gradlew test

rebuild: clean build install-dist
	@clear

build: checkstyle
	@./gradlew build

checkstyle:
	@./gradlew checkstyleMain
	@./gradlew checkstyleTest

install-dist:
	@./gradlew installDist

report:
	@./gradlew jacocoTestReport

run-dist:
	@./build/install/app/bin/app

clean:
	@./gradlew clean

