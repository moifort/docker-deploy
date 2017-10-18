package com.pupscan.deploy

import org.junit.Ignore
import org.junit.Test

@Ignore
class ShellTest {

    @Test
    fun test() {
        val shell = Shell()
        val imageName = "registry.gitlab.com/pupscan/configuration-web:test"
        val containerId = shell.run("""docker ps -qf ancestor=$imageName""")
    }
}