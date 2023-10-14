.PHONY: build

test:
	@./gradlew test

check:
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

