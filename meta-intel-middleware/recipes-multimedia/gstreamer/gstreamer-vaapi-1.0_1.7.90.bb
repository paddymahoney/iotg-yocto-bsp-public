require gstreamer-vaapi.inc

SRC_URI = "git://anongit.freedesktop.org/gstreamer/gstreamer-vaapi;branch=master;name=base \
           git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
           file://gstreamer-vaapi_version_diff.patch"

LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

SRCREV_base = "a486942ba2d44ba2f60fec25175c681786021b48"
SRCREV_common = "1b39f6d85a3d51ac6d1b44d8c821fd9b76b34454"
SRCREV_FORMAT = "base"

S = "${WORKDIR}/git"

DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad"

do_configure_prepend() {
        cd ${S}
        ./autogen.sh --noconfigure
        cd ${B}
}
