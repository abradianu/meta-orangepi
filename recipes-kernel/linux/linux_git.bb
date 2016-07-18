SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i|sun8i)"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require linux.inc

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Default is to use stable kernel version
# If you want to use latest git version set to "1"
DEFAULT_PREFERENCE = "-1" 

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
	
# 4.7rc7
PV = "4.7.7+git${SRCPV}"
SRCREV_pn-${PN} = "92d21ac74a9e3c09b0b01c764e530657e4c85c49"

SRC_URI = "\
        git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;branch=master \
        file://defconfig \
        "

SRC_URI_append_orangepi-lite += "\
        file://0001-wireless-realtek-Add-rtl8189fs-version-4.3.24.patch \
        file://0002-wireless-realtek-rtl8189fs-Update-for-kernel-4.7.patch \
        file://0003-ARM-dts-sun8i-Add-rtl8189fs-node.patch \
        file://0004-ARM-dts-sun8i-Add-ADS1115-support-for-Orange-Pi-One-.patch \
        "

S = "${WORKDIR}/git"