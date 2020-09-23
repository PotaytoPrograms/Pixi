#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoord0;

void main()
{
    gl_FragColor = vec4(v_texCoord0, 0.0f, 1.0f);
}
