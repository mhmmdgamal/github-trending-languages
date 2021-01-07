# Github Trending Languages
## List trending languages from github, simply translates to fetching the most starred repos created in the last 30 days ( from now )


#### Download

```
$ git clone https://github.com/mhmmdgamal/github-trending-languages.git
```

## DevOps

#### Prerequisite

Your operating system must have the JDK greater than or equal 1.8 installed, and it's recommended that you install the
 IDE to look up the source code.

#### Test

Check the test case with the `@Test` annotation.

```
On linux/Mac

$ ./mvnw test 

On Windows

> mvnw.cmd test
```

#### Build

Build the project and create executable jar and war files.

```
On linux/Mac

$ ./mvnw package 

On Windows

> mvnw.cmd package
```

#### Run

An example with spring-web-starter can be connected by Web Browser

```
On linux/Mac

$ ./mvnw spring-boot:run

On Windows

> mvnw.cmd spring-boot:run
```

```
GET http://localhost:8080/api/v1/github/trending/languages
```

## Example

#### Success

```
GET http://localhost:8080/api/v1/github/trending/languages
```

```json
{
    "code": 200,
    "languages": [
        {
            "name": "C#",
            "count": 4,
            "repositories": [
                {
                    "name": "taskbar-groups",
                    "url": "https://github.com/tjackenpacken/taskbar-groups"
                },
                {
                    "name": "SteamTools",
                    "url": "https://github.com/rmbadmin/SteamTools"
                }
            ]
        },
        {
            "name": "C",
            "count": 4,
            "repositories": [
                {
                    "name": "n64bootloader",
                    "url": "https://github.com/clbr/n64bootloader"
                },
                {
                    "name": "ByteDance-HIDS",
                    "url": "https://github.com/bytedance/ByteDance-HIDS"
                }
            ]
        }
    ]
}
```

#### Success

```
GET http://localhost:8080/api/v1/github/trending/languages
```
```json
{
    "code": 503,
    "languages": []
}
```

## Swagger Documentation

```
GET http://localhost:8080/swagger-ui
```