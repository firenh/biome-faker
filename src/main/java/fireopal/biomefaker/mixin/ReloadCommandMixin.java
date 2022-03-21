package fireopal.biomefaker.mixin;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fireopal.biomefaker.BiomeFaker;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.ReloadCommand;

@Mixin(ReloadCommand.class)
public class ReloadCommandMixin {
    @Inject(method = "reloadPacks", at = @At("RETURN"))
    private static void reloadPacks(Collection<String> dataPacks, CommandSourceStack source, CallbackInfo info) {
        try {
            BiomeFaker.reload();
        } catch (Exception e) {}
    }
}
