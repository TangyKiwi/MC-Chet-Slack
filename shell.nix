{ pkgs ? import <nixpkgs> {} }:
  pkgs.mkShell {
      buildInputs = [
        pkgs.jdk8
      ];

      nativeBuildInputs = [
        pkgs.autoPatchelfHook
      ];
}
