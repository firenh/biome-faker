package fireopal.biomefaker.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fireopal.biomefaker.BiomeFaker;
import net.minecraft.server.dedicated.DedicatedServer;

@Mixin(DedicatedServer.class)
public class DedicatedServerMixin {
    @Inject(method = "initServer", at = @At("TAIL"), cancellable = false)
    private void initServer(CallbackInfoReturnable<Boolean> cir) {
        BiomeFaker.reload();
    }
}
