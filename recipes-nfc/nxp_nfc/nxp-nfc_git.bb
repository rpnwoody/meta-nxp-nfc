# Copyright (C) 2016 NXP Semiconductors

DESCRIPTION = "Linux NFC stack for NCI based NXP NFC Controllers."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/include/nxp_nfc.h;endline=17;md5=42fdb99b3ff2c12f594b22a774cb7308"

SRC_URI = " \
    git://github.com/NXPNFCLinux/linux_libnfc-nci.git;branch=master \
    file://0001-cipher-update-openssl-API-call.patch \
    file://0002-Refactoring-of-the-nfc-library.patch \
    file://0003-Makefile.am-fix-m4-warning-and-dist-package.patch \
    file://0004-Use-pkg-config-to-support-openssl.patch \
    file://0005-Add-.gitignore.patch \
    file://0006-update-package-and-lib-versions.patch \
    file://0007-add-pkg-config-file-support.patch \
    file://0008-Makefile.am-preserve-LDFLAGS-from-environment.patch \
"
SRCREV = "2a9df3868507f4ee228067303b9aef973aa7598b"

inherit autotools pkgconfig lib_package

S = "${WORKDIR}/git"

# Enable llcp1_3 and set lib to latest pn7120 chip by default
PACKAGECONFIG ??= "llcp1_3 pn7120"
PACKAGECONFIG[llcp1_3] = "--enable-llcp1_3,--disable-llcp1_3,openssl,"
PACKAGECONFIG[pn7120] = "--enable-pn7120,--disable-pn7120,,"

# RDEPENDS on nxp-pn5xx kernel module
RDEPENDS_${PN} = "kernel-module-nxp-pn5xx"

