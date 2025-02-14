package net.caffeinemc.mods.sodium.client.model.color;

import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.model.quad.blender.BlendedColorProvider;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import java.util.Arrays;

public class DefaultColorProviders {
    public static ColorProvider<BlockState> adapt(BlockColor color) {
        return new VanillaAdapter(color);
    }

    public static class GrassColorProvider<T> extends BlendedColorProvider<T> {
        public static final ColorProvider<BlockState> BLOCKS = new GrassColorProvider<>();

        private GrassColorProvider() {

        }

        @Override
        protected int getColor(LevelSlice slice, BlockPos pos) {
            return BiomeColors.getAverageGrassColor(slice, pos);
        }
    }

    public static class FoliageColorProvider<T> extends BlendedColorProvider<T> {
        public static final ColorProvider<BlockState> BLOCKS = new FoliageColorProvider<>();

        private FoliageColorProvider() {

        }

        @Override
        protected int getColor(LevelSlice slice, BlockPos pos) {
            return BiomeColors.getAverageFoliageColor(slice, pos);
        }
    }

    public static class WaterColorProvider<T> extends BlendedColorProvider<T> {
        public static final ColorProvider<BlockState> BLOCKS = new WaterColorProvider<>();
        public static final ColorProvider<FluidState> FLUIDS = new WaterColorProvider<>();

        private WaterColorProvider() {

        }

        @Override
        protected int getColor(LevelSlice slice, BlockPos pos) {
            return BiomeColors.getAverageWaterColor(slice, pos);
        }
    }

    private static class VanillaAdapter implements ColorProvider<BlockState> {
        private final BlockColor color;

        private VanillaAdapter(BlockColor color) {
            this.color = color;
        }

        @Override
        public void getColors(LevelSlice slice, BlockPos pos, BlockPos.MutableBlockPos scratchPos, BlockState state, ModelQuadView quad, int[] output) {
            Arrays.fill(output, this.color.getColor(state, slice, pos, quad.getColorIndex()));
        }
    }
}
