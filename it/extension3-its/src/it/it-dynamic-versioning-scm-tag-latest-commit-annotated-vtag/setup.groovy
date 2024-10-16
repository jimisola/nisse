void exec(String[] command) {
    def proc = command.execute(null, basedir)
    proc.consumeProcessOutput(System.out, System.out)
    proc.waitFor()
    assert proc.exitValue() == 0 : "Command '${command}' returned status: ${proc.exitValue()}"
}

def testFile = new File(basedir, 'test.txt')
testFile << 'content'

exec('git', 'init')
exec('git', 'config', 'user.email', 'you@example.com')
exec('git', 'config', 'user.name', 'Your Name')

exec('git', 'add', 'test.txt')
exec('git', 'commit', '-m', 'initial-commit')
exec('git', 'tag', '-a', 'v1.1.1', '-m', 'v1.1.1 as annotated tag')
