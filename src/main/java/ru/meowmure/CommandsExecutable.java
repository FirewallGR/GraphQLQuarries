package ru.meowmure;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.inject.Inject;
import picocli.CommandLine;
import ru.meowmure.commands.FindCommand;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Model.UsageMessageSpec.SECTION_KEY_COMMAND_LIST;


@CommandLine.Command(name = "eco", subcommands = {FindCommand.class})
public class CommandsExecutable implements QuarkusApplication, Callable<Integer> {

    @Override
    public int run(String... args) throws Exception {
        CommandLine cmd = new CommandLine(this);

        //When running tests the cli should not prompt for user input.
        boolean interactiveMode = Arrays.stream(args).noneMatch(arg -> arg.equals("--cli-test"));
        Optional<String> testDir = Arrays.stream(args).dropWhile(arg -> !arg.equals("--cli-test-dir")).skip(1).findFirst();
        Quarkus.waitForExit();
        return cmd.execute(args);
    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
