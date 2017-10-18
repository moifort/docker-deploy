package com.pupscan.deploy

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Service

@SpringBootApplication
class DeployApplication

fun main(args: Array<String>) {
    SpringApplication.run(DeployApplication::class.java, *args)
}

@Service
class Shell : CommandLineRunner {
    override fun run(vararg arg: String?) {
        val bash = Runtime.getRuntime()
        val command = bash.exec("docker up -d docker")
        command.waitFor()
        println(command.exitValue())
    }

}
