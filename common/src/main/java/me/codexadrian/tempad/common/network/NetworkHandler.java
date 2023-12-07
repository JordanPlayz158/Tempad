package me.codexadrian.tempad.common.network;

import com.teamresourceful.resourcefullib.common.networking.NetworkChannel;
import com.teamresourceful.resourcefullib.common.networking.base.NetworkDirection;
import me.codexadrian.tempad.common.Tempad;
import me.codexadrian.tempad.common.network.messages.c2s.*;
import me.codexadrian.tempad.common.network.messages.s2c.InitConfigPacket;
import me.codexadrian.tempad.common.network.messages.s2c.OpenTempadScreenPacket;


public class NetworkHandler {
    public static final NetworkChannel CHANNEL = new NetworkChannel(Tempad.MODID, 0, "main");

    public static void register() {
        CHANNEL.registerPacket(NetworkDirection.CLIENT_TO_SERVER, AddLocationPacket.ID, AddLocationPacket.HANDLER, AddLocationPacket.class);
        CHANNEL.registerPacket(NetworkDirection.CLIENT_TO_SERVER, DeleteLocationPacket.ID, DeleteLocationPacket.HANDLER, DeleteLocationPacket.class);
        CHANNEL.registerPacket(NetworkDirection.CLIENT_TO_SERVER, ExportLocationPacket.ID, ExportLocationPacket.HANDLER, ExportLocationPacket.class);
        CHANNEL.registerPacket(NetworkDirection.CLIENT_TO_SERVER, FavoriteLocationPacket.ID, FavoriteLocationPacket.HANDLER, FavoriteLocationPacket.class);
        CHANNEL.registerPacket(NetworkDirection.CLIENT_TO_SERVER, OpenFavoritedLocationPacket.ID, OpenFavoritedLocationPacket.HANDLER, OpenFavoritedLocationPacket.class);
        CHANNEL.registerPacket(NetworkDirection.CLIENT_TO_SERVER, OpenTempadByShortcutPacket.ID, OpenTempadByShortcutPacket.HANDLER, OpenTempadByShortcutPacket.class);
        CHANNEL.registerPacket(NetworkDirection.CLIENT_TO_SERVER, SummonTimedoorPacket.ID, SummonTimedoorPacket.HANDLER, SummonTimedoorPacket.class);

        CHANNEL.registerPacket(NetworkDirection.SERVER_TO_CLIENT, InitConfigPacket.ID, InitConfigPacket.HANDLER, InitConfigPacket.class);
        CHANNEL.registerPacket(NetworkDirection.SERVER_TO_CLIENT, OpenTempadScreenPacket.ID, OpenTempadScreenPacket.HANDLER, OpenTempadScreenPacket.class);
    }
}