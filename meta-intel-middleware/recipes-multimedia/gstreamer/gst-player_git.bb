SUMMARY="GStreamer playback helper library and examples"

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base gtk+3 gstreamer1.0-plugins-bad"

SRC_URI = "git://github.com/sdroege/gst-player.git \
           file://gst-player.desktop"

SRCREV = "a1d371f34fb7ff317c1fbff780206530ffab6290"

S = "${WORKDIR}/git"

inherit autotools lib_package pkgconfig distro_features_check

ANY_OF_DISTRO_FEATURES = "${GTK2DISTROFEATURES}"

do_configure_prepend() {
	touch ${S}/ChangeLog
}

EXTRA_OECONF += "ac_cv_path_VALGRIND=no ac_cv_path_GDB=no"

do_install_append() {
	install -m 0644 -D ${WORKDIR}/gst-player.desktop ${D}${datadir}/applications/gst-player.desktop
}

FILES_${PN}-bin += "${datadir}/applications/*.desktop"

RDEPENDS_${PN}-bin = "gstreamer1.0-plugins-base-playback"
RRECOMMENDS_${PN}-bin = "gstreamer1.0-plugins-base-meta \
                         gstreamer1.0-plugins-good-meta \
                         gstreamer1.0-plugins-bad-meta \
                         ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "gstreamer1.0-libav", "", d)} \
                         ${@bb.utils.contains("LICENSE_FLAGS_WHITELIST", "commercial", "gstreamer1.0-plugins-ugly-meta", "", d)}"
