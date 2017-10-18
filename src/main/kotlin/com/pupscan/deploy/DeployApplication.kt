package com.pupscan.deploy

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

@SpringBootApplication
class DeployApplication

fun main(args: Array<String>) {
    SpringApplication.run(DeployApplication::class.java, *args)
}

@Service
class Shell : CommandLineRunner {
    private val log = LoggerFactory.getLogger(javaClass)!!

    override fun run(vararg arg: String?) {
        run("docker login -u tibo7n -p Moifort91! registry.gitlab.com")
        val imageName = "registry.gitlab.com/pupscan/configuration-web:test"
        run("docker pull $imageName")
        val containerId = run("docker ps -qf ancestor=$imageName")
        val commandToUpdateImate = run("runlike $containerId")
        run("docker stop $containerId")
        run("docker rm $containerId")
        run(commandToUpdateImate)
    }

    fun run(commandeToRun: String): String {
        log.info(commandeToRun)
        val command = Runtime.getRuntime().exec(commandeToRun)
        val output = print(command.inputStream)
        command.waitFor(10, TimeUnit.MINUTES)
        if (command.exitValue() != 0) {
            log.error(print(command.errorStream))
            return ""
        }
        log.info(output)
        return output
    }

    fun print(stream: InputStream) = stream.bufferedReader().use(BufferedReader::readText)

}
