.PHONY: build

test:
	@./gradlew test

checkstyle:
	@./gradlew checkstyleMain
	@./gradlew checkstyleTest

build:
	@check
	@./gradlew build

install-dist:
	@./gradlew installDist

rebuild:
	@./gradlew clean
	@check
	@build
	@install-dist
	@clear

report:
	@./gradlew jacocoTestReport


run-dist:
	@./build/install/app/bin/app

