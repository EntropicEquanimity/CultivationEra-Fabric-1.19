package net.entropicequanimity.cultera.block.entity;

import net.entropicequanimity.cultera.item.ModItems;
import net.entropicequanimity.cultera.screen.SpiritCollectorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SpiritCollectorBasicBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }
    protected  final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 200;
    public SpiritCollectorBasicBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPIRIT_COLLECTOR_ENTITY_BASIC, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> SpiritCollectorBasicBlockEntity.this.progress;
                    case 1 -> SpiritCollectorBasicBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> SpiritCollectorBasicBlockEntity.this.progress = value;
                    case 1 -> SpiritCollectorBasicBlockEntity.this.maxProgress = value;
                }
            }
            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.cultera.spirit_collector_basic");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SpiritCollectorScreenHandler(syncId, inv, this, this.propertyDelegate);
    }
    @Override protected void writeNbt(NbtCompound nbt){
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("spirit_collector_basic.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("spirit_collector_basic.progress");
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, SpiritCollectorBasicBlockEntity entity) {
        if(world.isClient()){return;}
        ItemStack inv = entity.getStack(0);



        if(entity.progress >= entity.maxProgress){
            if(canInsertAmountIntoSlot(entity, 1, 0)){
                addSpiritStoneToInventory(entity, 1, 0);
            }
            entity.progress = 0;
        }
        else {
            if(canInsertAmountIntoSlot(entity, 1, 0)){entity.progress++;}
        }
    }
    protected static void addSpiritStoneToInventory(SpiritCollectorBasicBlockEntity inventory, int count, int slot){
        ItemStack stack = inventory.getStack(slot);
        if(stack.isEmpty()){
            inventory.setStack(slot, new ItemStack(ModItems.SPIRIT_STONE_RANK_1));
        }
        else{ stack.increment(count); }
    }

    protected static boolean canInsertAmountIntoSlot(SpiritCollectorBasicBlockEntity entity, int count, int slot){
        return entity.getStack(slot).getMaxCount() > entity.getStack(slot).getCount() + count;
    }
}
